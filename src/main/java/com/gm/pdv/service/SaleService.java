package com.gm.pdv.service;

import com.gm.pdv.dto.ProductDTO;
import com.gm.pdv.dto.ProductInfoDTO;
import com.gm.pdv.dto.SaleDTO;
import com.gm.pdv.dto.SaleInfoDTO;
import com.gm.pdv.entity.ItemSale;
import com.gm.pdv.entity.Product;
import com.gm.pdv.entity.Sale;
import com.gm.pdv.entity.User;
import com.gm.pdv.repository.ItemSaleRepository;
import com.gm.pdv.repository.ProductRepository;
import com.gm.pdv.repository.SaleRepository;
import com.gm.pdv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SaleService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;

    public List<SaleInfoDTO> findAll(){
        return saleRepository.findAll().stream().map(sale -> getSaleInfo(sale)).collect(Collectors.toList());

    }

    private SaleInfoDTO getSaleInfo(Sale sale) {
        SaleInfoDTO saleInfoDTO = new SaleInfoDTO();
        saleInfoDTO.setUser(sale.getUser().getName());
        saleInfoDTO.setDate(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        saleInfoDTO.setProducts(getProductInfo(sale.getItems()));
        return saleInfoDTO;
    }

    private List<ProductInfoDTO> getProductInfo(List<ItemSale> items) {
        return items.stream().map(item -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            productInfoDTO.setDescription(item.getProduct().getDescription());
            productInfoDTO.setQuantity(item.getQuantity());
            return productInfoDTO;
        }).collect(Collectors.toList());

    }

    @Transactional
    public long save(SaleDTO sale){
        User user = userRepository.findById(sale.getUserid()).get();

        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setDate(LocalDate.now());
        List<ItemSale> items = getItemsSale(sale.getItems());

        newSale = saleRepository.save(newSale);

        saveItemsSale(items, newSale);

        return newSale.getId();
    }

    private void saveItemsSale(List<ItemSale> items, Sale newSale) {
        for(ItemSale item : items){
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemsSale(List<ProductDTO> products){

        return  products.stream().map(item -> {
            Product product = productRepository.getReferenceById(item.getProductid());

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());

            return itemSale;

        }).collect(Collectors.toList());


    }

    public SaleInfoDTO findById(long id) {
       Sale sale = saleRepository.findById(id).get();
      return getSaleInfo(sale);
    }
}
