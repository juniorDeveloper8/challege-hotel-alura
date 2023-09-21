
CREATE TABLE `huespedes` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `nom` VARCHAR(25) NOT NULL,
  `ape` VARCHAR(25) NOT NULL,
  `fechaN` DATE NOT NULL,
  `nacionalidad` VARCHAR(25) NOT NULL,
  `documento` VARCHAR(25) NOT NULL,
  `phone` VARCHAR(10) UNIQUE NOT NULL,
  `activo` TINYINT,
  `id_re` INT UNIQUE NOT NULL
);

CREATE TABLE `reservas` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `fechaEntrada` DATE NOT NULL,
  `fechaSalida` DATE NOT NULL,
  `valor` VARCHAR(5) NOT NULL,
  `formaP` VARCHAR(25) NOT NULL,
  `activo` TINYINT
);

CREATE TABLE `usario` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `login` VARCHAR UNIQUE NOT NULL,
  `clave` VARCHAR UNIQUE NOT NULL,
  `activo` TINYINT
);

ALTER TABLE `reservas` ADD FOREIGN KEY (`id`) REFERENCES `huespedes` (`id`);
