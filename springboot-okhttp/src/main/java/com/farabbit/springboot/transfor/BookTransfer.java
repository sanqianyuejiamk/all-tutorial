package com.farabbit.springboot.transfor;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.domain.BookDTO;
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
}
