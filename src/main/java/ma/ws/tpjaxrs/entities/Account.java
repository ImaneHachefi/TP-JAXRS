package ma.ws.tpjaxrs.entities;

import jakarta.persistence.*;
import lombok.* ;
import ma.ws.tpjaxrs.enumeration.AccountType;
import java.util.*;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double solde;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Enumerated(EnumType.ORDINAL)
    private AccountType type;

}
