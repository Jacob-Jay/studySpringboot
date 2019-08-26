package convert.function;

import convert.model.Person;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-26 14:25
 */
public class BeanWrapperTest {

    @Test
    public void t1() {
        Person person = new Person();
        BeanWrapper wrapper = new BeanWrapperImpl(person);
        wrapper.setPropertyValue("ok","1");
        System.out.println(person);
    }

    @Test
    public void t2() {
        Person person = new Person();
        BeanWrapperImpl wrapper = new BeanWrapperImpl(false);
        wrapper.setWrappedInstance(person);
        wrapper.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
        wrapper.setPropertyValue("birth","1999-02-02");
        wrapper.registerCustomEditor(Boolean.class,new CustomBooleanEditor("HAODE","HUAIDE",true));
        wrapper.registerCustomEditor(boolean.class,new CustomBooleanEditor("HAODE","HUAIDE",true));
        wrapper.setPropertyValue("ok","HAODE");
        System.out.println(person);
    }
}
