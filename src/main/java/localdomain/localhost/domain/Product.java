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
package localdomain.localhost.domain;

import localdomain.localhost.service.VatService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
@Configurable(autowire = Autowire.BY_TYPE)
public class Product implements Comparable<Product> {

    @Autowired
    private VatService vatService;
    /**
     * Price without VAT
     */
    private int netPriceInCents;
    private String name;

    public Product() {
    }

    /**
     * @param name
     * @param netPriceInCents price without VAT
     */
    public Product(String name, int netPriceInCents) {
        this.name = name;
        this.netPriceInCents = netPriceInCents;
    }

    public Product(VatService vatService) {
        this.vatService = vatService;
    }

    public VatService getVatService() {
        return vatService;
    }

    public void setVatService(VatService vatService) {
        this.vatService = vatService;
    }

    /**
     * @return Price without VAT
     */
    public int getNetPriceInCents() {
        return netPriceInCents;
    }

    public void setNetPriceInCents(int netPriceInCents) {
        this.netPriceInCents = netPriceInCents;
    }

    public String getNetPriceAsText() {
        BigDecimal netPriceInDollars = new BigDecimal(getNetPriceInCents()).movePointLeft(2);
        return NumberFormat.getCurrencyInstance(Locale.US).format(netPriceInDollars);
    }

    /**
     *
     * @return Price with VAT
     */
    public int getGrossPriceInCents() {
        BigDecimal grossPriceInCents = new BigDecimal(netPriceInCents).multiply(new BigDecimal(1000 + vatService.getVatInPerThousand())).movePointLeft(3);
        return grossPriceInCents.round(new MathContext(0, RoundingMode.HALF_UP)).intValue();
    }

    /**
     * <code>grossPriceInCents * (1 + (vatInPercent / 100))</code>
     *
     * @return Price with VAT
     */
    public String getGrossPriceAsText() {
        BigDecimal grossPriceInDollars = new BigDecimal(getGrossPriceInCents()).movePointLeft(2);
        return NumberFormat.getCurrencyInstance(Locale.US).format(grossPriceInDollars);
    }

    public int getVatInPerThousand() {
        return vatService.getVatInPerThousand();
    }

    public String getVatAsText() {
        BigDecimal vatInPercent = new BigDecimal(getVatInPerThousand()).movePointLeft(3);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.US);
        percentFormat.setMaximumFractionDigits(1);
        percentFormat.setRoundingMode(RoundingMode.HALF_DOWN);
        return percentFormat.format(vatInPercent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }
}
