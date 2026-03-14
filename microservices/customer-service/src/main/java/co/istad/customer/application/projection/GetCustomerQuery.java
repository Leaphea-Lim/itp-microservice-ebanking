package co.istad.customer.application.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCustomerQuery {

    private Integer pageNumber;
    private Integer pageSize;

}
