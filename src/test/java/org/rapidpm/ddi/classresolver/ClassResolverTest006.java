package org.rapidpm.ddi.classresolver;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.implresolver.DDIModelException;

import javax.inject.Inject;

/**
 * Created by svenruppert on 05.08.15.
 */
public class ClassResolverTest006 {

  @Test(expected = DDIModelException.class)
  public void testProducer001() throws Exception {

    final BusinessModule businessModule = new BusinessModule();
    try {
      DI.getInstance().activateDI(businessModule);
    } catch (DDIModelException e) {
      final String message = e.getMessage();
      System.out.println("message = " + message);
      Assert.assertTrue(message.contains("only interfaces found for interface"));
      throw e;
    }
    Assert.fail();
  }

  public static class BusinessModule {
    @Inject Service service;
    public String work(String txt) {
      return service.work(txt);
    }
  }

  public interface Service {
    String work(String txt);
  }

}
