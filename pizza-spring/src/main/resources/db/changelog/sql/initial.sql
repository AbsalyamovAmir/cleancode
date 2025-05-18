-- liquibase formatted sql

-- changeset absalyamov:1
CREATE TABLE kitchen_type (
                              id serial PRIMARY KEY,
                              name varchar(50) UNIQUE NOT NULL,
                              description varchar(500)
);

-- changeset absalyamov:2
CREATE TABLE customer (
                          id serial PRIMARY KEY,
                          name varchar(50) NOT NULL,
                          phone varchar(20) UNIQUE NOT NULL
);

-- changeset absalyamov:3
CREATE TABLE category_dish (
                               id serial PRIMARY KEY,
                               name varchar(50) UNIQUE NOT NULL,
                               kitchen_type_id bigint NOT NULL,
                               CONSTRAINT fk_dish_kitchen FOREIGN KEY (kitchen_type_id) REFERENCES kitchen_type(id) ON DELETE RESTRICT
);

-- changeset absalyamov:4
CREATE TABLE restaurant (
                            id serial PRIMARY KEY,
                            name varchar(50) NOT NULL,
                            kitchen_type_id bigint NOT NULL,
                            is_working boolean DEFAULT TRUE,
                            CONSTRAINT fk_restaurant_kitchen FOREIGN KEY (kitchen_type_id) REFERENCES kitchen_type(id) ON DELETE RESTRICT
);

-- changeset absalyamov:5
CREATE TABLE dish (
                      id serial PRIMARY KEY,
                      name varchar(50) UNIQUE NOT NULL,
                      price decimal(10, 2) NOT NULL,
                      category_dish_id bigint NOT NULL,
                      CONSTRAINT fk_dish_category FOREIGN KEY (category_dish_id) REFERENCES category_dish(id) ON DELETE RESTRICT
);

-- changeset absalyamov:6
CREATE TABLE customer_order (
                                id serial PRIMARY KEY,
                                customer_id bigint NOT NULL,
                                created_at timestamp DEFAULT CURRENT_TIMESTAMP,
                                restaurant_id bigint NOT NULL,
                                status varchar(20) NOT NULL,
                                total_price decimal(10, 2) NOT NULL,
                                CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE,
                                CONSTRAINT fk_order_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant(id) ON DELETE RESTRICT
);

-- changeset absalyamov:7
CREATE TABLE ingredient (
                            id serial PRIMARY KEY,
                            name varchar(50) UNIQUE NOT NULL,
                            weight int NOT NULL,
                            restaurant_id bigint NOT NULL,
                            CONSTRAINT fk_ingrediend_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant(id) ON DELETE CASCADE
);

-- changeset absalyamov:8
CREATE TABLE order_item (
                            id serial PRIMARY KEY,
                            customer_order_id bigint NOT NULL,
                            dish_id bigint NOT NULL,
                            quantity int NOT NULL,
                            price_at_order decimal(10, 2) NOT NULL,
                            CONSTRAINT fk_item_order FOREIGN KEY (customer_order_id) REFERENCES customer_order(id) ON DELETE CASCADE,
                            CONSTRAINT fk_item_dish FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE RESTRICT
);

-- changeset absalyamov:9
CREATE TABLE dish_ingredient (
                                 dish_id bigint NOT NULL,
                                 ingredient_id bigint NOT NULL,
                                 PRIMARY KEY (dish_id, ingredient_id),
                                 CONSTRAINT fk_ingredient_dish_dish FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE,
                                 CONSTRAINT fk_ingredient_dish_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient(id) ON DELETE RESTRICT
);

-- changeset absalyamov:10
CREATE INDEX idx_customer_order_customer_id ON customer_order(customer_id);
CREATE INDEX idx_customer_order_restaurant_id ON customer_order(restaurant_id);
CREATE INDEX idx_customer_order_status ON customer_order(status);

-- changeset absalyamov:11
CREATE INDEX idx_dish_category_id ON dish(category_dish_id);
CREATE INDEX idx_ingredient_restaurant_id ON ingredient(restaurant_id);
CREATE INDEX idx_category_dish_kitchen_type ON category_dish(kitchen_type_id);

-- changeset absalyamov:12
CREATE INDEX idx_restaurant_kitchen_type ON restaurant(kitchen_type_id);
CREATE INDEX idx_order_item_order ON order_item(customer_order_id);
CREATE INDEX idx_order_item_dish ON order_item(dish_id);