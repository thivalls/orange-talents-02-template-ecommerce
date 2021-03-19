package br.com.zup.mercadolivre.external;

import br.com.zup.mercadolivre.order.Order;
import br.com.zup.mercadolivre.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_ranking")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    @ManyToOne
    private User user;

    public Ranking(Order order, User user) {
        this.order = order;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Nf{" +
                "id=" + id +
                ", order=" + order +
                ", user=" + user +
                '}';
    }
}
