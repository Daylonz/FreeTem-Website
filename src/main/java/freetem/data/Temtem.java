package freetem.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daylon
 * created 2/10/2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temtem {
    private int id;
    private String name;
    private int startingPrice;
    private int catchRate;
}
