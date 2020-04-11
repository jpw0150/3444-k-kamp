<?php

	class orderOperations{
		private $con;
		
		function __construct(){
			require_once dirname(__FILE__) . '/DbConnect.php';
			$db = new DbConnect;
			$this->con = $db->connect();
		}
		
		public function createOrder($tableNum, $entree, $side, $drink, $orderTotal){
			$state = $this->con->prepare("INSERT INTO orders (tableNum, entree, side, drink, orderTotal) VALUES (?, ?, ?, ?, ?)");
			
			$state->bind_param("isssd", $tableNum, $entree, $side, $drink, $orderTotal);
			
			if($state->execute()){
				return ORDER_CREATE;
			}
			else{
				return ORDER_FAIL;
			}			
		}
		
		public function allorders(){
			$state = $this->con->prepare("SELECT * FROM orders");
			$state->execute();
			$state->bind_result($id, $tableNum, $entree, $side, $drink, $orderTotal);			
			$orders = array();
			while($state->fetch()){
				$order = array();
				$order['id'] = $id;
				$order['tableNum'] = $tableNum;
				$order['entree'] = $entree;
				$order['side'] = $side;
				$order['drink'] = $drink;
				$order['orderTotal'] = $orderTotal;
				array_push($orders, $order);
			}
			return $orders;
		}
		
		public function findOrder($id){
			$state = $this->con->prepare("SELECT * FROM orders WHERE id = ?");
			$state->bind_param("i", $id);
			$state->execute();
			$state->bind_result($id, $tableNum, $entree, $side, $drink, $orderTotal);
			$state->fetch();
			$order = array();
			$order['id'] = $id;
			$order['tableNum'] = $tableNum;
			$order['entree'] = $entree;
			$order['side'] = $side;
			$order['drink'] = $drink;
			$order['orderTotal'] = $orderTotal;
			return $order;
		}
		
		public function orderExist($id){
			$state = $this->con->prepare("SELECT * FROM orders WHERE id = ?");
			$state->bind_param("i", $id);
			$state->execute();
			$state->store_result();
			if ($state->num_rows > 0) return true;
			return false;
		}
		
		public function updateorder($id, $tableNum, $entree, $side, $drink, $orderTotal){
            $state = $this->con->prepare("UPDATE orders SET tableNum = ?, entree = ?, side = ?, drink = ?, orderTotal = ? WHERE id = ?");
            $state->bind_param("isssdi", $tableNum, $entree, $side, $drink, $orderTotal, $id);
            if($state->execute())
                return true;
            return false; 
		}
		
        public function deleteOrder($id){
            $state = $this->con->prepare("DELETE FROM orders WHERE id = ?");
            $state->bind_param("i", $id);
            if($state->execute())
                return true; 
            return false; 
        }
        public function clearQueue(){
            $state = $this->con->prepare("DELETE FROM orders");
            if($state->execute())
                return true; 
            return false; 
        }
	}