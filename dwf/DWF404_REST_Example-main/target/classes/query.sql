CREATE DATABASE tienda;
USE tienda;
CREATE TABLE Producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT DEFAULT NULL,
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    categoria VARCHAR(50) NOT NULL,
    stock INT NOT NULL CHECK (stock >= 0),
    disponible BOOLEAN NOT NULL DEFAULT TRUE
);




CREATE TABLE Cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(15) DEFAULT NULL,
    direccion VARCHAR(255) DEFAULT NULL,
    fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN NOT NULL DEFAULT TRUE
    );



CREATE TABLE Pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    fecha_pedido TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('PENDIENTE', 'EN PREPARACIÓN', 'ENTREGADO', 'CANCELADO') NOT NULL,
    metodo_pago ENUM('EFECTIVO', 'TARJETA', 'TRANSFERENCIA') NOT NULL,
    total DECIMAL(10,2) NOT NULL CHECK (total >= 0),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);




CREATE TABLE Pedido_Producto (
    pedido_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10,2) NOT NULL CHECK (precio_unitario > 0),
    subtotal DECIMAL(10,2) NOT NULL CHECK (subtotal >= 0),
    PRIMARY KEY (pedido_id, producto_id),
    FOREIGN KEY (pedido_id) REFERENCES Pedido(id),
    FOREIGN KEY (producto_id) REFERENCES Producto(id)
);


CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO Cliente (nombre, email, telefono, direccion) VALUES
('Juan Pérez', 'juan@example.com', '123456789', 'Calle Falsa 123'),
('María López', 'maria@example.com', '987654321', 'Avenida Siempre Viva 742'),
('Carlos Sánchez', 'carlos@example.com', '555444333', 'Paseo del Prado 10');

RENAME TABLE Cliente TO Usuario;
RENAME TABLE usuario TO Usuarios;


INSERT INTO Producto (nombre, descripcion, precio, categoria, stock, disponible) VALUES
('Camiseta Básica', 'Camiseta de algodón de alta calidad', 12.99, 'Ropa', 50, TRUE),
('Pantalón Jeans', 'Pantalón de mezclilla cómodo y duradero', 29.99, 'Ropa', 40, TRUE),
('Zapatos Deportivos', 'Zapatos deportivos con buena amortiguación', 59.99, 'Calzado', 30, TRUE),
('Mochila Escolar', 'Mochila resistente para estudiantes', 25.49, 'Accesorios', 20, TRUE),
('Auriculares Bluetooth', 'Auriculares inalámbricos de calidad', 35.99, 'Tecnología', 15, TRUE),
('Reloj Inteligente', 'Reloj con múltiples funciones y monitoreo de salud', 149.99, 'Tecnología', 10, TRUE),
('Silla de Oficina', 'Silla ergonómica para oficina', 85.99, 'Muebles', 25, TRUE),
('Lámpara LED', 'Lámpara LED regulable para escritorio', 19.99, 'Muebles', 40, TRUE),
('Smartphone Samsung', 'Teléfono inteligente con pantalla AMOLED', 499.99, 'Tecnología', 12, TRUE),
('Laptop HP', 'Laptop portátil con procesador Intel i7', 799.99, 'Tecnología', 8, TRUE),
('Cafetera', 'Cafetera de cápsulas de alta eficiencia', 59.99, 'Electrodomésticos', 35, TRUE),
('Tetera Eléctrica', 'Tetera eléctrica para preparar té rápidamente', 24.99, 'Electrodomésticos', 18, TRUE),
('Juego de Ollas', 'Juego de ollas de acero inoxidable', 99.99, 'Cocina', 50, TRUE),
('Sofá Moderno', 'Sofá grande para salón, diseño moderno', 899.99, 'Muebles', 5, TRUE),
('Mesa de Comedor', 'Mesa de comedor de madera con 6 sillas', 299.99, 'Muebles', 10, TRUE),
('Cargador Rápido', 'Cargador rápido para dispositivos móviles', 15.99, 'Accesorios', 60, TRUE),
('Parlante Bluetooth', 'Parlante inalámbrico para fiestas y reuniones', 49.99, 'Tecnología', 22, TRUE),
('Microondas', 'Microondas con varias funciones', 99.99, 'Electrodomésticos', 18, TRUE),
('Plancha de Vapor', 'Plancha de vapor para ropa', 45.99, 'Electrodomésticos', 40, TRUE),
('Secador de Pelo', 'Secador de pelo profesional', 39.99, 'Electrodomésticos', 35, TRUE),
('Cámara Digital', 'Cámara de fotos digital con 12 MP', 129.99, 'Tecnología', 15, TRUE),
('Gafas de Sol', 'Gafas de sol UV400', 19.99, 'Accesorios', 45, TRUE),
('Pulsera Fitness', 'Pulsera para monitorear actividad física', 24.99, 'Tecnología', 25, TRUE),
('Bicicleta de Montaña', 'Bicicleta para todo terreno con 18 velocidades', 250.00, 'Deportes', 10, TRUE),
('Raqueta de Tenis', 'Raqueta profesional de tenis', 89.99, 'Deportes', 30, TRUE),
('Pelota de Fútbol', 'Pelota de fútbol profesional', 19.99, 'Deportes', 40, TRUE),
('Bota de Fútbol', 'Bota de fútbol para césped sintético', 79.99, 'Calzado', 50, TRUE);

SHOW tables;

INSERT INTO Pedido (cliente_id, estado, metodo_pago, total) VALUES
(1, 'PENDIENTE', 'EFECTIVO', 42.98),
(2, 'EN PREPARACIÓN', 'TARJETA', 109.98),
(3, 'ENTREGADO', 'TRANSFERENCIA', 19.99),
(1, 'CANCELADO', 'TARJETA', 79.99);


INSERT INTO Pedido_Producto (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(1, 4, 1, 25.49, 25.49);


INSERT INTO Pedido_Producto (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(2, 6, 1, 149.99, 149.99);


INSERT INTO Pedido_Producto (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(3, 17, 1, 49.99, 49.99),
(3, 16, 1, 15.99, 15.99);
