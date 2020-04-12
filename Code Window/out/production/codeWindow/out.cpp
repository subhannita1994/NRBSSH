int something(){

	return 1;
}
int main()
{
  int *p;
   {
     int local1;

     p = &local1;
     local1 = 10;
     
   }
   {
      int local2;
      local2 = 20;
      
   }

   if (*p == 10)  // out of scope use of local1
     {

     }
     return something();


}

