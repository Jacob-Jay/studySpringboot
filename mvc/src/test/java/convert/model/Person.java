package convert.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-26 14:24
 */
@Data
public class Person {
    private String name;
    private int age;
    private Date birth;
    private boolean ok;
}
