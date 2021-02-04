from django.db import models

class Post(models.Model):
    name = models.TextField()
    vote = models.TextField()
    timestamp = models.DateTimeField(auto_now=True)
    