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
package junit.com.svenruppert.ddi.producer.v008;

import junit.com.svenruppert.ddi.DDIBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.svenruppert.ddi.DDIModelException;
import com.svenruppert.ddi.DI;
import com.svenruppert.ddi.Produces;
import com.svenruppert.ddi.producer.Producer;

import javax.inject.Inject;

public class Producer008Test
    extends DDIBaseTest {


  @Test()
  public void test001() {
    Service service = new Service();
    Assertions.assertThrows(DDIModelException.class, ()-> DI.activateDI(service));

  }

  public interface SubService {
  }

  public class Service{
    @Inject SubService subService;
  }

  @Produces(SubService.class)
  public class ServiceProducer implements Producer<SubService>{

    @Override
    public SubService create() {
      throw new RuntimeException("something bad happened");
    }
  }
}
