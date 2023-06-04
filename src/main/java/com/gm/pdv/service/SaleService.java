package com.gm.pdv.service;

import com.gm.pdv.dto.ProductDTO;
import com.gm.pdv.dto.SaleDTO;
import com.gm.pdv.entity.ItemSale;
import com.gm.pdv.entity.Product;
import com.gm.pdv.entity.Sale;
import com.gm.pdv.entity.User;
import com.gm.pdv.repository.ItemSaleRepository;
import com.gm.pdv.repository.ProductRepository;
import com.gm.pdv.repository.SaleRepository;
import com.gm.pdv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SaleService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;

    @Transactional
    public long save(SaleDTO  sale){
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
}
