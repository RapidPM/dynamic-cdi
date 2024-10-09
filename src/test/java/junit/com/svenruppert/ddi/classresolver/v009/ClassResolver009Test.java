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
package junit.com.svenruppert.ddi.classresolver.v009;

import junit.com.svenruppert.ddi.DDIBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.svenruppert.ddi.DI;
import com.svenruppert.ddi.Produces;
import com.svenruppert.ddi.producer.Producer;

import javax.inject.Inject;

public class ClassResolver009Test
    extends DDIBaseTest {

  /**
   * 1 Interface, 2 impl, 1 Producer for Interface -> Producer will be used
   *
   */
  @Test
  public void testProxy001() {
    final BusinessModul instance = new BusinessModul();
    Assertions.assertNotNull(instance);
    Assertions.assertNull(instance.service);
    DI.activateDI(instance);

    Assertions.assertNotNull(instance.service);

    final String hello = instance.service.doWork("Hello");
    Assertions.assertNotNull(hello);
    Assertions.assertEquals("created by Producer", hello);
  }

  @Test
  public void testProxy002() {
    final BusinessModulVirtual instance = new BusinessModulVirtual();
    Assertions.assertNotNull(instance);
    Assertions.assertNull(instance.service);
    DI.activateDI(instance);

    Assertions.assertNotNull(instance.service);

//    Assertions.assertTrue(java.lang.reflect.Proxy.isProxyClass(instance.service.getClass()));
    final String hello = instance.service.doWork("Hello");
    Assertions.assertNotNull(hello);
    Assertions.assertEquals("created by Producer", hello);
  }


  public interface Service {
    String doWork(String str);
  }

  public static class BusinessModul {
    @Inject Service service;

    public String work(String str) {
      return service.doWork(str);
    }
  }

  public static class BusinessModulVirtual {
    @Inject Service service;

    public String work(String str) {
      return service.doWork(str);
    }
  }

  public static class ServiceA implements Service {

    public ServiceA() {
      System.out.println(" ServiceA = constructed...");
    }

    @Override
    public String doWork(final String str) {
      return "ServiceA_" + str;
    }
  }


  @Produces(Service.class)
  public static class ServiceProducer implements Producer<Service> {
    @Override
    public Service create() {
      return new Service() { //this is a implementation of the Interface...
        @Override
        public String doWork(final String str) {
          return "created by Producer";
        }
      };
    }
  }

}
