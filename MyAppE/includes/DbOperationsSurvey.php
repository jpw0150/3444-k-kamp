<?php

    class DbOperationsSurvey{

        private $con;

        function __construct(){
            require_once dirname(__FILE__) . '/DbConnect.php';
            $db = new DbConnect;
            $this->con = $db->connect();
        }

        public function createSurvey($questionOne, $questionTwo, $questionThree){
            $stmt = $this->con->prepare("INSERT INTO customerSurvey (questionOne, questionTwo, questionThree) VALUES (?, ?, ?)");
            $stmt->bind_param("sss", $questionOne, $questionTwo, $questionThree);
            if($stmt->execute()){
                return ITEM_CREATED;
            }else{
                return ITEM_FAILURE;
            }
        }

        public function getAllSurveys(){
            $stmt = $this->con->prepare("SELECT questionOne, questionTwo, questionThree FROM customerSurvey");
            $stmt->execute();
            $stmt->bind_result($questionOne, $questionTwo, $questionThree);
            $survey = array();
            while($stmt->fetch()){
                $svy = array();
                $svy['questionOne'] = $questionOne;
                $svy['questionTwo'] = $questionTwo;
                $svy['questionThree'] = $questionThree;
                array_push($survey, $svy);
            }
            return $survey;
        }
   
    }
