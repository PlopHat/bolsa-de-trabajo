package cl.utem.bolsadetrabajo_backend.utils;

import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageUtils {
  public static Pageable getPageable(PaginationQueriesDto queries) {
    Sort sort = null;
    if(queries.getSortBy()!=null) {
      Sort.Direction direction = queries.getSortDirection() != null && queries.getSortDirection().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
      sort = Sort.by(direction, queries.getSortBy());
    }

    Pageable pageable = sort != null ?
            PageRequest.of(queries.getPage(), queries.getPageSize(), sort) : PageRequest.of(queries.getPage(), queries.getPageSize());
    return pageable;
  }
}
