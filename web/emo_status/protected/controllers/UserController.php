<?php

class UserController extends Controller
{
	public function actionIndex()
	{
		$this->render('index');
	}

	// Uncomment the following methods and override them if needed
	/*
	public function filters()
	{
		// return the filter configuration for this controller, e.g.:
		return array(
			'inlineFilterName',
			array(
				'class'=>'path.to.FilterClass',
				'propertyName'=>'propertyValue',
			),-create-form')
	    {
	        echo CActiveForm::validate($model);
	        Yii::app()->end();
	    }
	    

	    if(isset($_POST['User']))
	    {
	        $model->attributes=$_POST['User'];
	        
	        // sha1 for password
	        $attrs = $model->attributes;
	        $attrs['password'] = sha1($model->attributes['password']);
	        
	        $model->attributes = $attrs;
	        
		);
	}

	public function actions()
	{
		// return external action classes, e.g.:
		return array(
			'action1'=>'path.to.ActionClass',
			'action2'=>array(
				'class'=>'path.to.AnotherActionClass',
				'propertyName'=>'propertyValue',
			),
		);
	}
	*/

	public function actionCreate()
	{

		$model=new User;

	    // uncomment the following code to enable ajax-based validation
	    
	    if(isset($_POST['ajax']) && $_POST['ajax']==='user-create-form')
	    {
	        echo CActiveForm::validate($model);
	        Yii::app()->end();
	    }
	    

	    if(isset($_POST['User']))
	    {
	        $model->attributes=$_POST['User'];
	        
	        // sha1 for password
	        $attrs = $model->attributes;
	        $attrs['password'] = sha1($model->attributes['password']);
	        
	        $model->attributes = $attrs;
	        
	        if($model->validate())
	        {
	            if($model->save())
	            {
	            	Yii::app()->user->setFlash('success','Cuenta creada con éxito.');
					$this->refresh();
				}
				else
				{
					Yii::app()->user->setFlash('error','Ha habido un error al crear la cuenta. Intente más tarde nuevamente.');
					$this->refresh();	
				}
	        }
	    }
	    $this->render('create',array('model'=>$model));
	}


	/**
	 * Updates a particular model.
	 * If update is successful, the browser will be redirected to the 'view' page.
	 * @param integer $id the ID of the model to be updated
	 */
	public function actionUpdate($id)
	{
		$model=$this->loadModel($id);
		/*
		// Uncomment the following line if AJAX validation is needed
		// $this->performAjaxValidation($model);

		if(isset($_POST['<?php echo $this->modelClass; ?>']))
		{
			$model->attributes=$_POST['<?php echo $this->modelClass; ?>'];
			if($model->save())
				$this->redirect(array('view','id'=>$model-><?php echo $this->tableSchema->primaryKey; ?>));
		}

		$this->render('update',array(
			'model'=>$model,
		));*/
	}

	/**
	 * Deletes a particular model.
	 * If deletion is successful, the browser will be redirected to the 'admin' page.
	 * @param integer $id the ID of the model to be deleted
	 */
	public function actionDelete($id)
	{
		/*$this->loadModel($id)->delete();

		// if AJAX request (triggered by deletion via admin grid view), we should not redirect the browser
		if(!isset($_GET['ajax']))
			$this->redirect(isset($_POST['returnUrl']) ? $_POST['returnUrl'] : array('admin'));*/
	}
}