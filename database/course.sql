-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  jeu. 21 mars 2019 à 10:49
-- Version du serveur :  8.0.13-4
-- Version de PHP :  7.2.15-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bHykARFGFP`
--

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `id_enseignant` varchar(20) NOT NULL,
  `course` varchar(30) NOT NULL,
  `begin` datetime NOT NULL,
  `end` datetime NOT NULL,
  `groupe` varchar(12) NOT NULL,
  `filiere` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `course`
--

INSERT INTO `course` (`id`, `id_enseignant`, `course`, `begin`, `end`, `groupe`, `filiere`) VALUES
(23, '5342', 'Reseau', '2019-03-20 08:20:00', '2019-03-20 09:30:00', 'A', ''),
(24, '105', 'OS', '2019-03-20 09:54:00', '2019-03-20 17:54:00', 'A', ''),
(25, '5342', 'GPI', '2019-03-20 16:10:00', '2019-03-20 17:07:00', 'L3', 'A'),
(26, '5342', 'GPI', '2019-03-20 17:50:00', '2019-03-20 19:04:00', 'L3-I', 'A'),
(27, '5342', 'GPI', '2019-03-20 17:50:00', '2019-03-20 19:04:00', 'A', 'L3I'),
(28, '5342', 'GPI', '2019-03-20 18:20:00', '2019-03-20 20:20:00', 'A', 'L3I'),
(29, '105', 'OS', '2019-03-21 08:45:00', '2019-03-21 08:55:00', 'A', 'L3I'),
(30, '5342', 'GPI', '2019-03-21 10:30:00', '2019-03-21 10:55:00', 'A', 'L3I'),
(31, '5342', 'GPI', '2019-03-21 10:30:00', '2019-03-21 10:55:00', 'A', 'L3I');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
