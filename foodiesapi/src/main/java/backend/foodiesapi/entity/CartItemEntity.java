package backend.foodiesapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemId; // Food ID

    @Column(nullable = false)
    private int quantity;

    // Use itemId to fetch Food details if needed, or store basic food details here
    // to avoid join complexity
    // For now keeping it simple as per original usage

    public CartItemEntity() {
    }

    public CartItemEntity(Long id, String itemId, int quantity) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public static CartItemEntityBuilder builder() {
        return new CartItemEntityBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static class CartItemEntityBuilder {
        private Long id;
        private String itemId;
        private int quantity;

        public CartItemEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CartItemEntityBuilder itemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public CartItemEntityBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public CartItemEntity build() {
            return new CartItemEntity(id, itemId, quantity);
        }
    }
}
