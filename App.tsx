/* eslint-disable react-native/no-inline-styles */
import 'react-native-gesture-handler';
import 'react-native-reanimated';
import * as React from 'react';
import SplashScreen from 'react-native-splash-screen';
import {Pressable, Text, View} from 'react-native';
import {printReceipt, readCard} from './NexgoInterface';
import {NativeModules} from 'react-native';

function App() {
  React.useState(() => {
    SplashScreen.hide();
  });

  const handleClick = async () => {
    try {
      const printres = await printReceipt(
        'Comprehensive',
        'Kenechukwu Josiah',
        'NBC Bank',
        '12361983791237984',
        '500,500,400',
      );
      console.log('clicked', {printres});
    } catch (error) {
      console.log(error);
    }
  };

  const handleCardReading = async () => {
    // const cardInfo = await readCard();
    const cardInfo = NativeModules.NexgoModule.readCard();
    console.log({cardInfo});
  };

  // Initialize NexGo SDK
  return (
    <>
      <View style={{padding: 20}}>
        <Pressable
          onPress={handleClick}
          style={{
            backgroundColor: 'purple',
            padding: 10,
            borderRadius: 20,
            marginBottom: 10,
          }}>
          <Text style={{textAlign: 'center', color: 'white'}}>
            Print Recipt
          </Text>
        </Pressable>
        <Pressable
          onPress={handleCardReading}
          style={{
            borderColor: 'purple',
            borderWidth: 2,
            padding: 10,
            borderRadius: 20,
          }}>
          <Text style={{textAlign: 'center', color: 'purple'}}>Read Card</Text>
        </Pressable>
      </View>
    </>
  );
  // return <Router />;
}

export default App;
