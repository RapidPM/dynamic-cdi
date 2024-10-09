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
package junit.com.svenruppert.ddi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.svenruppert.ddi.DI;

public class DDIBaseTest {


  @BeforeEach
  public void setUpDDI() {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    final String name = this.getClass().getPackage().getName();
    DI.activatePackages(name);
    DI.activateDI(this);
  }

  @AfterEach
  public void tearDownDDI() {
    DI.clearReflectionModel();
  }
}
