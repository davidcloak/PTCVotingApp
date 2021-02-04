from django.shortcuts import render
#JSON response
from django.http import JsonResponse

from rest_framework.views import APIView
from rest_framework.response import Response

#get request
class TestView(APIView):
    def get(self, request, *args, **kwargs):
        data = {
        'name': 'Fengqi',
        'vote': 'Donald Trump',
        'party': 'Republican',
        }
        return Response(data)



