FROM python:3.8
WORKDIR /usr/src/app
COPY . .
RUN python3 -m pip install pika
RUN python3 -m pip install numpy
RUN pip install requests


#CMD ["rabbit:5672","--","python3","data_generator.py"]
ENTRYPOINT ["python3","data_generator.py"]