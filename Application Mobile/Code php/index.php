<?php
  if(isset($_GET['tag']) && $_GET['tag']!=''){
    $tag = $_GET['tag'];
    require_once 'DBGTAS.php';
    $db = new DB_Functions();
    $response = array("tag" => $tag, "error" => false);
    if($tag == 'connectionEtu'){
      $name = $_GET['nom'];
      $password = $_GET['password'];
      $user = $db -> getEtu($name, $password);
      if($user){
        $response["error"] = false;
        $response["num_etu"] = $user["num_etu"];
        $response["user"]["nom"] = $user["nom"];
        $response["user"]["password"] = $user["password"];
        echo json_encode($response);
      }else{
        $response["error"] = true;
        echo json_encode($response);
      }
    }
    if($tag == 'connectionPro'){
      $name = $_GET['nom'];
      $password = $_GET['password'];
      $user = $db -> getProf($name, $password);
      if($user){
        $response["error"] = false;
        $response["id_enseignant"] = $user["id_enseignant"];
        $response["user"]["nom"] = $user["nom"];
        $response["user"]["password"] = $user["password"];
        echo json_encode($response);
      }else{
        $response["error"] = true;
        echo json_encode($response);
      }
    }
    if($tag == 'history'){
      $id = $_GET['student'];
      $user = $db -> history($id);
      if($user){
        echo json_encode($user);
      }
      else{
        $response["error"] = true;
        echo json_encode($response);
      }
    }
    if($tag == 'scan'){
      $student = $_GET['student'];
      $matiere = $_GET['matiere'];
      $begin = $_GET['begin'];
      $arrival = $_GET['arrival'];
      $end = $_GET['end'];
      $user = $db -> scan($student, $matiere, $begin, $arrival, $end);
      if($user){
        $response["error"] = false;
        $response["user"] = $user;
        echo json_encode($response);
      }
      else {
        $response["error"] = true;
        echo json_encode($response);
      }
    }

    if($tag == 'course'){
      $teacher = $_GET['teacher'];
      $matiere = $_GET['course'];
      $begin = $_GET['begin'];
      $end = $_GET['end'];
      $group = $_GET['group'];
      $filiere = $_GET['filiere'];
      $user = $db -> registerCourse($teacher, $matiere, $begin, $end, $group, $filiere);
      if($user){
        $response["error"] = false;
        $response["user"] = $user;
        echo json_encode($response);
      }
      else {
        $response["error"] = true;
        echo json_encode($response);
      }
    }

    if($tag == 'getMatiere'){
      $idProf = $_GET['idProf'];
      $user = $db -> getMatiere($idProf);
      if($user){
        $response["error"] = false;
        $response["user"] = $user;
        echo json_encode($response);
      }else {
        $response["error"] = true;
        echo json_encode($response);
      }
    }


  }

 ?>
