<?php

    class ingredientsOperations{

        private $con;

        function __construct(){
            require_once dirname(__FILE__) . '/DbConnect.php';
            $db = new DbConnect;
            $this->con = $db->connect();
        }

        public function createFood($id, $food, $amount){
            if(!$this->isFoodExist($food)){
                 $stmt = $this->con->prepare("INSERT INTO ingredients (id, food, amount) VALUES (?, ?, ?)");
                 $stmt->bind_param("isi",$id, $food, $amount);
                 if($stmt->execute()){
                     return ING_CREATE;
                 }else{
                     return ING_FAIL;
                 }
            }
            return ING_EXIST;
         }

		public function getInfo($id){
			$state = $this->con->prepare("SELECT food, amount FROM ingredients WHERE id = ?");
			$state->bind_param("i", $id);
			$state->execute();
			$state->bind_result($food, $amount);
			$state->fetch();
			$returnarray = array();
			$returnarray['food'] = $food;
			$returnarray['amount'] = $amount;
			return $returnarray;
		}

         public function getAllIngredients(){
            $stmt = $this->con->prepare("SELECT food, amount FROM ingredients");
            $stmt->execute();
            $stmt->bind_result($food, $amount);
            $ingts = array();
            while($stmt->fetch()){
                $ing = array();
                $ing['food'] = $food;
                $ing['amount'] = $amount;
                array_push($ingts, $ing);
            }
            return $ingts;
        }

        public function getFoodByName($food){
            $stmt = $this->con->prepare("SELECT food, amount, id FROM ingredients WHERE food = ?");
            $stmt->bind_param("s", $food);
            $stmt->execute();
            $stmt->bind_result($food, $amount, $id);
            $stmt->fetch();
            $ing = array();
            $ing['food'] = $food;
            $ing['amount'] = $amount;
            $ing['id'] = $id;
            return $ing;
        }

        public function updateIngredients($food, $amount, $id){
             $stmt = $this->con->prepare("UPDATE ingredients SET food = ?, amount = ? WHERE id = ?");
             $stmt->bind_param("sii", $food, $amount, $id);
             if($stmt->execute())
                return true;
            return false;
         }

    
        public function trashedFood($id){
            $stmt = $this->con->prepare("DELETE FROM ingredients WHERE id = ?");
            $stmt->bind_param("i", $id);
            if($stmt->execute())
                return true;
            return false;
        }

         private function isFoodExist($food){
            //$stmt = $this->con->prepare("SELECT id FROM ingredient WHERE food = ?");
            $stmt = $this->con->prepare("SELECT id FROM ingredients WHERE food = ?");
            $stmt->bind_param("s", $food);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

    }
