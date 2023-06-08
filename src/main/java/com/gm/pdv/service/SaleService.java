package com.gm.pdv.service;

import com.gm.pdv.dto.ProductSaleDTO;
import com.gm.pdv.dto.ProductInfoDTO;
import com.gm.pdv.dto.SaleDTO;
import com.gm.pdv.dto.SaleInfoDTO;
import com.gm.pdv.entity.ItemSale;
import com.gm.pdv.entity.Product;
import com.gm.pdv.entity.Sale;
import com.gm.pdv.entity.User;
import com.gm.pdv.exceptions.InvalidOperationException;
import com.gm.pdv.exceptions.NoItemException;
import com.gm.pdv.repository.ItemSaleRepository;
import com.gm.pdv.repository.ProductRepository;
import com.gm.pdv.repository.SaleRepository;
import com.gm.pdv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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
        var products = getProductInfo(sale.getItems());
        BigDecimal total = getTotal(products);
        return SaleInfoDTO.builder()
                .user(sale.getUser().getName())
                .date(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .products(products)
                .total(total)
                .build();
    }

    private BigDecimal getTotal(List<ProductInfoDTO> products) {
        BigDecimal total = new BigDecimal(0);

        for(int i = 0; i < products.size();i++){
            ProductInfoDTO currentProduct = products.get(i);
            total = total.add(currentProduct.getPrice()
                    .multiply(new BigDecimal(currentProduct.getQuantity())));
        }
        return  total;
    }

    private List<ProductInfoDTO> getProductInfo(List<ItemSale> items) {
        if (CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }

        return items.stream().map(
                item ->ProductInfoDTO
                        .builder()
                        .id(item.getId())
                        .price(item.getProduct().getPrice())
                        .description(item.getProduct().getDescription())
                        .quantity(item.getQuantity()).build()
            ).collect(Collectors.toList());
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

    private List<ItemSale> getItemsSale(List<ProductSaleDTO> products){

        return  products.stream().map(item -> {
            Product product = productRepository.getReferenceById(item.getProductid());

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());
            
            if (product.getQuantity() == 0){
                throw new NoItemException("Product not available in stock.");
            } else if (product.getQuantity() < item.getQuantity()) {
                throw new InvalidOperationException(
                        String.format("Quantity of items from the sale (%s) " +
                        "is less than the quantity available in stock (%s) ", item.getQuantity(), product.getQuantity()));
            }

            int total = product.getQuantity() - item.getQuantity();
            product.setQuantity(total);

            return itemSale;

        }).collect(Collectors.toList());
    }

    public SaleInfoDTO findById(long id) {
       Sale sale = saleRepository.findById(id).get();
      return getSaleInfo(sale);
    }
}
