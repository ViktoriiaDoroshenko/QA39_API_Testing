package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Builder
@Setter
@Getter
@ToString
public class ContactListDTO {

    List<ContactDTO> contacts;

}
