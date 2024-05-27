package com.mkyong.transfor;

import com.mkyong.t_01.BookDO;
import com.mkyong.t_01.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author mengka
 * @version 2021/4/29
 * @since
 */
@Mapper(componentModel = "spring")
public interface BookTransfer extends BaseTransfer<BookDO,BookDTO> {

    BookTransfer INSTANCE = Mappers.getMapper(BookTransfer.class);

    @Mappings({
       @Mapping(source = "email",target = "email2"),
       @Mapping(source = "bookAddr.addr",target = "addr"),
       @Mapping(source = "bookAddr.addrId",target = "addrId")
    })
    BookDTO toModel(BookDO entity);
}
