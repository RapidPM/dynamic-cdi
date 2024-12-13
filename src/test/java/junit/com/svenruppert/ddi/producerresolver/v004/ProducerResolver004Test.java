/*
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
package junit.com.svenruppert.ddi.producerresolver.v004;

import junit.com.svenruppert.ddi.DDIBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.svenruppert.ddi.DDIModelException;
import com.svenruppert.ddi.DI;
import com.svenruppert.ddi.Produces;
import com.svenruppert.ddi.producer.Producer;
import com.svenruppert.ddi.producerresolver.ProducerResolver;

public class ProducerResolver004Test
    extends DDIBaseTest{

  @Test()
  public void test001() {
    try {
      DI.activateDI(MyService.class);
    } catch (Exception e) {
      Assertions.assertTrue(e instanceof DDIModelException);
      Assertions.assertTrue(e.getMessage().contains("to many Producer and no ProducerResolver found for interface"));
    }
  }

  public interface MyService {
    String doWork(String txt);
  }

  @Produces(MyService.class)
  public static class Producer_A_1 implements Producer<MyService> {
    @Override
    public MyService create() {
      return null;
    }
  }

  @Produces(MyService.class)
  public static class Producer_A_2 implements Producer<MyService> {
    @Override
    public MyService create() {
      return null;
    }
  }

//  @ResponsibleFor(MyService.class)
  public static class MyProducerResolver_A implements ProducerResolver<MyService,Producer<MyService>> {
    @Override
    public Class resolve(final Class<? extends MyService> interf) {
      return Producer_A_2.class;
    }
  }
}
