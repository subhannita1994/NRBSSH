#include <iostream> 
  
class Test { 
private: 
	Test(){}
    ~Test() {} 
}; 
int main() 
{ 
    Test* t;
    int avar = 20;
    std::cout<<avar;
    return 0;
}