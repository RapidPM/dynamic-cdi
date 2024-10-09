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
package junit.com.svenruppert.ddi.producer.v007;

import junit.com.svenruppert.ddi.DDIBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.svenruppert.ddi.DI;
import com.svenruppert.ddi.Produces;
import com.svenruppert.ddi.producer.Producer;

import javax.inject.Inject;

public class Producer007Test
    extends DDIBaseTest {



  @Inject Service service;

  @Test
  public void test001() {
    DI.activateDI(this);
    Assertions.assertEquals(service.getClass(), ServiceImpl_B.class);
  }

  public interface Service{}

  public static class ServiceImpl_B implements Service{}

  @Produces(ServiceImpl_B.class)
  public static class ServiceImpl_B_Producer implements Producer<Service> {
    @Override
    public Service create() {
     return  new ServiceImpl_B();
    }
  }



}
