package graphqlasclient.service;

import graphqlasclient.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImpInventory  implements  InventoryService{

    @Autowired
    private HttpGraphQlClient httpGraphQlClient;

    @Override
    public List<Product>  viewProduct() {
        String getAllProductQuery="query MyQuery {\n" +
                "  getAllProducts {\n" +
                "    name\n" +
                "    price\n" +
                "    stock\n" +
                "  }\n" +
                "}";
       return   httpGraphQlClient.document(getAllProductQuery).retrieve("getAllProducts").toEntityList(Product.class).block();
    }

    @Override
    public List<Product> getAllProductByCate(String category) {
        String getAllProductByCateQuery= String.format("query MyQuery {\n" +
                "  getProductByCategory(category: \"%s\") {\n" +
                "    category\n" +
                "    id\n" +
                "    name\n" +
                "  }\n" +
                "}",category);
        return  httpGraphQlClient.document(getAllProductByCateQuery).retrieve("getProductByCategory").toEntityList(Product.class).block();

    }
}
