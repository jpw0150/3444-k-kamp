# 3444-k-kamp
sup lads
what??

<?php

declare(strict_types=1);

use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use Slim\Factory\AppFactory;

require __DIR__ . '/../vendor/autoload.php';

require __DIR__ . '/../includes/DbOperations.php';

$app = AppFactory::create();
$app->addErrorMiddleWare(true, true, false);



/*
    endpoint: createingredient
    parameters: food, foodnum
    method: POST
*/
$app->post('/createuser', function(Request $request, Response $response){
    if(!haveEmptyParameters(array('food', 'foodnum'), $response)){

        $request_data = $request->getParsedBody();

        $food = $request_data['food'];
        $foodnum = $request_data['foodnum'];

        $db = new DbOperations;

        $result = $db->createUser($food, $foodnum);

        if($result == name_CREATED){

            $message = array();
            $message['error'] = false;
            $message['message'] = 'User created successfully';

            $response->getbody()->write(json_encode($message));

             return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);

        }else if($result == name_FAILURE){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'Error occured';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);

        }else if($result == name_EXISTS){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'User Already Created';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);

        }
    }
    return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);
});

function haveEmptyParameters($require_params, $response){
    $error = false;
    $error_params = '';
    $request_params = $_REQUEST;

    foreach($require_params as $param){
        if(!isset($request_params[$param]) || strlen($request_params[$param])<=0){
            $error = true;
            $error_params .= $param . ', ';
        }
    }

    if($error){
        $error_detail = array();
        $error_detail['error'] = true;
        $error_detail['message'] = 'Required parameters ' . substr($error_params, 0, -2) . ' are missing or empty';
        $response->getbody()->write(json_encode($error_detail));
    }
    return $error;
}

$app->run();









<?php

    class DbOperations{

        private $con;

        function __construct(){
            require_once dirname(__FILE__) . '/DbConnect.php';
            $db = new DbConnect;
            $this->con = $db->connect();

        }

        public function createUser($food, $foodnum){
            if(!$this->isnameExist($food)){
            $stmt = $this->con->prepare("INSERT INTO ingredient (food, foodnum) VALUES (?, ?)");
            $stmt->bind_param("si", $food, $foodnum);
            if($stmt->execute()){
                return name_CREATED;
            }else{
                return name_FAILURE;
            }
            }
            return name_EXISTS;
        }

        private function isnameExist($food){
            //$stmt = $this->con->prepare("SELECT id FROM ingredient WHERE food = ?");
            $stmt = $this->con->prepare("SELECT id FROM ingredient WHERE food = ?");
            $stmt->bind_param("s", $food);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

    }
