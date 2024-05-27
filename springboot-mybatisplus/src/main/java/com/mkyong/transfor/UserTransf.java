package com.mkyong.transfor;

import com.mkyong.model.User;
import com.mkyong.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Mapper
public interface UserTransf {

    UserTransf INSTANCE = Mappers.getMapper(UserTransf.class);

    @Mappings({
       @Mapping(source = "email",target = "email2")
    })
    UserDTO toDTO(User user);
}
