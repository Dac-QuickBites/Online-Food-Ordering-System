package backend.foodiesapi.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // link to User

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private List<CartItemEntity> items; // list of items in the cart

    public CartEntity() {
    }

    public CartEntity(Long id, Long userId, List<CartItemEntity> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    public static CartEntityBuilder builder() {
        return new CartEntityBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemEntity> getItems() {
        return items;
    }

    public void setItems(List<CartItemEntity> items) {
        this.items = items;
    }

    public static class CartEntityBuilder {
        private Long id;
        private Long userId;
        private List<CartItemEntity> items;

        public CartEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CartEntityBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public CartEntityBuilder items(List<CartItemEntity> items) {
            this.items = items;
            return this;
        }

        public CartEntity build() {
            return new CartEntity(id, userId, items);
        }
    }
}
