<?php


use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use Slim\Factory\AppFactory;

require __DIR__ . '/../vendor/autoload.php';
require __DIR__ . '/../includes/DbOperationsSurvey.php';



$app = AppFactory::create();
$app->addRoutingMiddleware();
$errorMiddleware = $app->addErrorMiddleware(true, true, true);

/*
	endpoint: createsurvey
	parameters: question1, question2, question3
	method: POST
*/

// Creates a Survey
$app->post('/MyAppE/public/createsurvey', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('questionOne', 'questionTwo', 'questionThree'), $request, $response)){

		$request_data = $request->getParsedBody();

        $questionOne = $request_data['questionOne'];
		$questionTwo = $request_data['questionTwo'];
		$questionThree = $request_data['questionThree'];

        $db = new DbOperationsSurvey;

        $result = $db->createSurvey($questionOne, $questionTwo, $questionThree);

        if($result == ITEM_CREATED){

            $message = array();
            $message['error'] = false;
            $message['message'] = 'Survey Completed!';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);

        }else if($result == ITEM_FAILURE){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'Some error occurred';

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

/*
    endpoint: allsurveys
    method: GET
*/

// Shows all Surveys
$app->get('/MyAppE/public/allsurveys', function(Request $request, Response $response){

	$db = new DbOperationsSurvey;

	$emps = $db->getAllSurveys();

	$response_data = array();

	$response_data['error'] = false;
	$response_data['emps'] = $emps;

	$response->getbody()->write(json_encode($response_data));

	return $response
        ->withHeader('Content-type', 'application/json')
		->withStatus(200);

});


function haveEmptyParameters($required_params, $request, $response){
    $error = false;
	$error_params = '';
	//$request_params = $request->getParsedBody();
    $reqBody = file_get_contents('php://input');
    parse_str($reqBody, $request_params);

    foreach($required_params as $param){
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
        //$response->getbody()->write($error_detail);
    }
    return $error;
}

$app->run();
