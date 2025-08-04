/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.registry.integration;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.apache.dubbo.common.constants.CommonConstants.INTERFACE_KEY;
import static org.apache.dubbo.registry.Constants.SIMPLIFIED_KEY;
import static org.apache.dubbo.common.constants.CommonConstants.EXTRA_KEYS_KEY;

public class DefaultServiceURLCustomizerTest {

    @Test
    public void testCustomizeWithSimplifyModeAndDifferentPath() {
        URL url1 = URL.valueOf("dubbo://120.0.0.1:20880/org.apache.dubbo.demo.DemoService?key=value");
        DefaultServiceURLCustomizer customizer = new DefaultServiceURLCustomizer();
        URL customizedUrl1 = customizer.customize(url1, ApplicationModel.defaultModel());
        Assertions.assertEquals("org.apache.dubbo.demo.DemoService", customizedUrl1.getParameter(INTERFACE_KEY));

        URL url2 = URL.valueOf("dubbo://120.0.0.1:20880/org.apache.dubbo.demo.DemoService?key=value&interface=org.apache.dubbo.demo.DemoService");
        url2 = url2.putAttribute(SIMPLIFIED_KEY, true);
        URL customizedUrl2 = customizer.customize(url2, ApplicationModel.defaultModel());
        Assertions.assertNull(customizedUrl2.getParameter(INTERFACE_KEY));

        URL url3 = URL.valueOf("dubbo://120.0.0.1:20880/path-value?key=value&interface=org.apache.dubbo.demo.DemoService");
        url3 = url3.putAttribute(SIMPLIFIED_KEY, true);
        URL customizedUrl3 = customizer.customize(url3, ApplicationModel.defaultModel());
        Assertions.assertEquals("org.apache.dubbo.demo.DemoService", customizedUrl3.getParameter(INTERFACE_KEY));
    }
}
