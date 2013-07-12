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
package localdomain.localhost.service;

import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
@Service
public class VatService {

    /**
     * VAT in per thousand. We prefer "per thousand" to "per cent" to handle integers rather than decimals.
     * <p/>
     * Samples:
     * <ul>
     * <li>VAT of <code>20%</code> returns <code>200</code></li>
     * <li>VAT of <code>17.5%</code> returns <code>175</code></li>
     * </ul>
     *
     * @return the VAT in per thousand
     */
    public int getVatInPerThousand() {
        return 175;
    }
}
