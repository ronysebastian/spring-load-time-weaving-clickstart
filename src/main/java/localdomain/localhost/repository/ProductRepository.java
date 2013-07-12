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
package localdomain.localhost.repository;

import localdomain.localhost.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<Product>();

    public ProductRepository() {
        products.add(new Product("2 oz. Bees Wax", 450));
        products.add(new Product("5 oz. Bees Wax", 900));

        products.add(new Product("1 lb. Pure Local Raw Honey", 800));
        products.add(new Product("3 lb. Pure Local Raw Honey", 180));

        Collections.sort(products);
    }

    public List<Product> findProducts() {
        return new ArrayList<Product>(products);
    }
}
