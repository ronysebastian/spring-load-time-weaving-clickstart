/*
 * Copyright 2010-2013, CloudBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package localdomain.localhost.web;

import localdomain.localhost.domain.Products;
import localdomain.localhost.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
@Controller
public class ProductController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/products")
    public String showProducts(Map<String, Object> model) {
        // Here we are returning an object of type 'Products' rather than a collection of Product objects
        // so it is simpler for Object-Xml mapping
        Products products = new Products();
        products.getProductList().addAll(this.productRepository.findProducts());
        model.put("products", products);
        logger.trace("/products: {}", products);
        return "product/view-all";
    }
}
