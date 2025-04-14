package sv.edu.udb.spring_rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Setter
@Getter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido = new Date();

    @Column(nullable = false)
    private Double total;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoProducto> productos;
}
