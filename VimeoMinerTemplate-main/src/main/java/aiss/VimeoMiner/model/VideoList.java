package aiss.VimeoMiner.model;

import com.fasterxml.jackson.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VideoList {
        @JsonProperty("page")
        private Integer page;
        @JsonProperty("per_page")
        private Integer perPage;
        @JsonProperty("total")
        private Integer total;
        @JsonProperty("total_pages")
        private Integer totalPages;
        @JsonProperty("data")
        private List<Video> data;

        @JsonIgnore
        private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

        @JsonProperty("page")
        public Integer getPage() {
            return page;
        }

        @JsonProperty("page")
        public void setPage(Integer page) {
            this.page = page;
        }

        @JsonProperty("per_page")
        public Integer getPerPage() {
            return perPage;
        }

        @JsonProperty("per_page")
        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        @JsonProperty("total")
        public Integer getTotal() {
            return total;
        }

        @JsonProperty("total")
        public void setTotal(Integer total) {
            this.total = total;
        }

        @JsonProperty("total_pages")
        public Integer getTotalPages() {
            return totalPages;
        }

        @JsonProperty("total_pages")
        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        @JsonProperty("data")
        public List<Video> getData() {
            return data;
        }

        @JsonProperty("data")
        public void setData(List<Video> data) {
            this.data = data;
        }



        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(VideoList.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("page");
            sb.append('=');
            sb.append(((this.page == null)?"<null>":this.page));
            sb.append(',');
            sb.append("perPage");
            sb.append('=');
            sb.append(((this.perPage == null)?"<null>":this.perPage));
            sb.append(',');
            sb.append("total");
            sb.append('=');
            sb.append(((this.total == null)?"<null>":this.total));
            sb.append(',');
            sb.append("totalPages");
            sb.append('=');
            sb.append(((this.totalPages == null)?"<null>":this.totalPages));
            sb.append(',');
            sb.append("data");
            sb.append('=');
            sb.append(((this.data == null)?"<null>":this.data));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }
}


