  package org.glassfish.jersey.examples.helloworld;
  import org.glassfish.hk2.utilities.binding.AbstractBinder;
  public class AppBinder extends AbstractBinder {

        @Override
        protected void configure() {
            // singleton instance binding
            bind(new CustomerStore()).to(CustomerStore.class);

        }
    }
