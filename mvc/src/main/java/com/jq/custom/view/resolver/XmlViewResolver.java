package com.jq.custom.view.resolver;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Locale;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-24 10:59
 */
public class XmlViewResolver implements ViewResolver {
    private Jaxb2Marshaller jaxb2Marshaller;

    public XmlViewResolver(Jaxb2Marshaller jaxb2Marshaller) {
        this.jaxb2Marshaller = jaxb2Marshaller;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        MarshallingView view = new MarshallingView();
        view.setMarshaller(jaxb2Marshaller);
        return view;
    }
}
