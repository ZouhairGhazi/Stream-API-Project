package fr.codebusters.Stream.API.Project.services;

import fr.codebusters.Stream.API.Project.model.Product;
import fr.codebusters.Stream.API.Project.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategoryAndMinPrice(String category, Double price) {
        return getAll().stream()
                .filter(x -> x.getCategory().equals(category) && x.getPrice() > price)
                .toList();
    }

    public void applyDiscountOnProductByCategory(String category, Double discount) {
        List<Product> productList = getAll().stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();

        for (Product product : productList) {
            Double old_price = product.getPrice();
            Double new_price = old_price - (old_price * discount / 100);
            product.setPrice(new_price);
        }
    }

    public List<Product> getProductsByTierAndOrderDate(Integer tier, LocalDate minDate, LocalDate maxDate) {
        return getAll().stream()
                .filter(product -> product.getOrders().stream()
                        .anyMatch(order -> order.getCustomer().getTier().equals(tier) && order.getOrderDate().isAfter(minDate) && order.getOrderDate().isBefore(maxDate)))
                .toList();
    }

    public Product getCheapestProductByCategory(String category) {
        return getAll().stream()
                .filter(product -> product.getCategory().equals(category))
                .min(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

}
