package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaginationQueriesDto {
  @JsonProperty(value = "page")
  private int page;

  @JsonProperty(value = "pageSize")
  private int size;

  @JsonProperty(value = "offset")
  private int offset;

  @JsonProperty(value = "orderBy")
  private String sortBy;

  @JsonProperty(value = "direction")
  private String sortDirection;

}
