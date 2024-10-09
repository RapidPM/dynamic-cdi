/**
 * Copyright © 2013 Sven Ruppert (sven.ruppert@gmail.com)
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
package junit.com.svenruppert.ddi.base;

import junit.com.svenruppert.ddi.DDIBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

public class DI005Test
    extends DDIBaseTest {

  @Inject Service service;

  @Test
  public void test001() {
    Assertions.assertNotNull(service);
    Assertions.assertEquals(service.getClass(), ServiceImpl_A.class);
  }

  public interface Service {
    String doWork(String txt);
  }

  public static class ServiceImpl_A implements Service {
    @Override
    public String doWork(final String txt) {
      return "Nase - " + txt;
    }
  }

}
