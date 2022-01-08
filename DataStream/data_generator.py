#!/usr/bin/env python
import sys
import argparse
import numpy as np
import json
import time
import asyncio
import os
import pika
from random import randint

#---------------------------------------------------------------------

'''
Notes:
    -  used an Normal (Gaussian) Distribution for the random values
    -  but they don't actually make sense bc the values don't increase and deacrease that much in 
       only 10 sec in real life (is that a problem?)
    -  used async functions to run all data generation coroutines at once
    -  all values come from the same sensor? how we aggregate the sensors of the same patient
    -  the data is being generated but not being sent yet
'''


#---------------------------------------------------------------------

class Generator:

    def __init__(self):
        time.sleep(1000)
        
        self.sensor_id = 1
        self.connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
        self.channel = self.connection.channel()
        self.channel.queue_declare(queue='heartbeat')
        self.channel.queue_declare(queue='blood_pressure')
        self.channel.queue_declare(queue='temperature')
        self.channel.queue_declare(queue='sugar_level')
    

    async def gen_heart_beats(self):
        mu = 80
        sigma = 10.0

        while True:
            hb = np.random.randn() * sigma + mu
            json_text = {'id': self.sensor_id, 'heartbeat': int(hb)}
            print(json_text)
            self.channel.basic_publish(exchange='', routing_key='heartbeat', body=json.dumps(json_text))
            await asyncio.sleep(5)


    async def gen_blood_pressure(self):
        systolic_mu = 130
        diastolic_mu = 85
        sigma = 20.0

        while True:
            systolic = np.random.randn() * sigma + systolic_mu
            diastolic = np.random.randn() * sigma + diastolic_mu        
            json_text = {'id': self.sensor_id, 'systolic': round(float(systolic),2), 'diastolic': round(float(diastolic),2)}
            print(json_text)
            self.channel.basic_publish(exchange='', routing_key='blood_pressure', body=json.dumps(json_text))
            await asyncio.sleep(5)

    
    async def gen_body_temp(self):
        mu = 37
        sigma = 1.0

        while True:
            temperature = np.random.randn() * sigma + mu       
            json_text = {'id': self.sensor_id, 'temperature': round(float(temperature),2)}
            print(json_text)
            self.channel.basic_publish(exchange='', routing_key='temperature', body=json.dumps(json_text))
            await asyncio.sleep(5)

    
    async def gen_sugar_level(self):
        mu = 5.0
        sigma = 1.0

        while True:
            sugar = np.random.randn() * sigma + mu
            json_text = {'id': self.sensor_id, 'sugar': float(sugar)}
            print(json_text)
            self.channel.basic_publish(exchange='', routing_key='sugar_level', body=json.dumps(json_text))
            await asyncio.sleep(5)


if __name__ == "__main__":

    # arguments (maybe)
    parser = argparse.ArgumentParser()

    args = parser.parse_args()
    
    # generator class
    g = Generator()


    loop = asyncio.get_event_loop()

    hb_task = loop.create_task(g.gen_heart_beats())
    bp_task = loop.create_task(g.gen_blood_pressure())
    bt_task = loop.create_task(g.gen_body_temp())
    sl_task = loop.create_task(g.gen_sugar_level())

    # run them
    loop.run_until_complete(asyncio.gather(hb_task, bp_task, bt_task, sl_task,))

    #loop close
    loop.close()


