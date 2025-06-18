package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaginationQueriesDto {
  @JsonProperty(value = "page")
  private int page;

  @JsonProperty(value = "pageSize")
  private int pageSize;

  @JsonProperty(value = "offset")
  private int offset;

  @JsonProperty(value = "sortBy")
  private String sortBy;

  @JsonProperty(value = "sortDirection")
  private String sortDirection;

}
