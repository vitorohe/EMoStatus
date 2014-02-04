<?php

/**
 * This is the model class for table "monitored_users".
 *
 * The followings are the available columns in table 'monitored_users':
 * @property integer $id
 * @property string $username
 * @property string $full_name
 * @property string $description
 * @property integer $monitoring_activated
 * @property integer $monit_skype_activated
 * @property integer $monit_record_activated
 * @property integer $alert_sms_activated
 * @property integer $alert_email_activated
 *
 * The followings are the available model relations:
 * @property EmailAlertMonitoredUsers $emailAlertMonitoredUsers
 * @property Users[] $users
 * @property SmsAlertMonitoredUsers $smsAlertMonitoredUsers
 * @property Status[] $statuses
 */
class MonitoredUser extends CActiveRecord
{
	/**
	 * @return string the associated database table name
	 */
	public function tableName()
	{
		return 'monitored_users';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('username, full_name, description, monitoring_activated, monit_skype_activated, monit_record_activated, alert_sms_activated, alert_email_activated', 'required'),
			array('monitoring_activated, monit_skype_activated, monit_record_activated, alert_sms_activated, alert_email_activated', 'boolean'),
			array('username, full_name', 'length', 'max'=>45),
			// The following rule is used by search().
			// @todo Please remove those attributes that should not be searched.
			array('id, username, full_name, description, monitoring_activated, monit_skype_activated, monit_record_activated, alert_sms_activated, alert_email_activated', 'safe', 'on'=>'search'),
		);
	}

	/**
	 * @return array relational rules.
	 */
	public function relations()
	{
		// NOTE: you may need to adjust the relation name and the related
		// class name for the relations automatically generated below.
		return array(
			'emailAlertMonitoredUsers' => array(self::HAS_ONE, 'EmailAlertMonitoredUsers', 'monitored_user_id'),
			'users' => array(self::MANY_MANY, 'Users', 'monitorings(monitorized_user_id, user_id)'),
			'smsAlertMonitoredUsers' => array(self::HAS_ONE, 'SmsAlertMonitoredUsers', 'monitored_user_id'),
			'statuses' => array(self::MANY_MANY, 'Status', 'status_monitored_users(monitored_user_id, status_id)'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'id' => 'ID',
			'username' => 'Nombre de usuario',
			'full_name' => 'Nombre',
			'description' => 'Descripción',
			'monitoring_activated' => 'Monitoreo activado',
			'monit_skype_activated' => 'Monitoreo por Skype activado',
			'monit_record_activated' => 'Monitoreo por Grabación activado',
			'alert_sms_activated' => 'Alertas por Sms activadas',
			'alert_email_activated' => 'Alertas por Correo electrónico activadas',
		);
	}

	/**
	 * Retrieves a list of models based on the current search/filter conditions.
	 *
	 * Typical usecase:
	 * - Initialize the model fields with values from filter form.
	 * - Execute this method to get CActiveDataProvider instance which will filter
	 * models according to data in model fields.
	 * - Pass data provider to CGridView, CListView or any similar widget.
	 *
	 * @return CActiveDataProvider the data provider that can return the models
	 * based on the search/filter conditions.
	 */
	public function search()
	{
		// @todo Please modify the following code to remove attributes that should not be searched.

		$criteria=new CDbCriteria;

		$criteria->compare('id',$this->id);
		$criteria->compare('username',$this->username,true);
		$criteria->compare('full_name',$this->full_name,true);
		$criteria->compare('description',$this->description,true);
		$criteria->compare('monitoring_activated',$this->monitoring_activated);
		$criteria->compare('monit_skype_activated',$this->monit_skype_activated);
		$criteria->compare('monit_record_activated',$this->monit_record_activated);
		$criteria->compare('alert_sms_activated',$this->alert_sms_activated);
		$criteria->compare('alert_email_activated',$this->alert_email_activated);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}

	/**
	 * Returns the static model of the specified AR class.
	 * Please note that you should have this exact method in all your CActiveRecord descendants!
	 * @param string $className active record class name.
	 * @return MonitoredUser the static model class
	 */
	public static function model($className=__CLASS__)
	{
		return parent::model($className);
	}
}
