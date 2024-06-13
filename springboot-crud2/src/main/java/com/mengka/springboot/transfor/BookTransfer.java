package com.mengka.springboot.transfor;

import com.mengka.springboot.domain.Book;
import com.mengka.springboot.domain.BookDTO;
import com.mengka.springboot.domain.User;
import com.mengka.springboot.domain.UserLocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author mengka
 * @Date 2022-01-22 21:16
 */
@Mapper
public interface BookTransfer {

    BookTransfer INSTANCE = Mappers.getMapper(BookTransfer.class);

    @Mappings({
            @Mapping(source = "name",target = "name2")
    })
    BookDTO toDTO(Book book);

    @Mappings({
            @Mapping(source = "id",target = "userId"),
            @Mapping(source = "email",target = "email"),
            @Mapping(source = "location.place",target = "place"),
            @Mapping(source = "location.longitude",target = "longitude"),
            @Mapping(source = "location.latitude",target = "latitude")
    })
    UserLocationDTO toDTO(User user);
}
