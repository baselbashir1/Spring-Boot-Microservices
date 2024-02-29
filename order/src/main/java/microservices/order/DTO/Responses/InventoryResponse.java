package microservices.order.DTO.Responses;

public class InventoryResponse {
    private String skuCode;
    private Boolean isInStock;

    public InventoryResponse() {
    }

    public InventoryResponse(String skuCode, Boolean isInStock) {
        this.skuCode = skuCode;
        this.isInStock = isInStock;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuCode() {
        return this.skuCode;
    }

    public void setIsInStock(Boolean isInStock) {
        this.isInStock = isInStock;
    }

    public Boolean getIsInStock() {
        return this.isInStock;
    }
}
